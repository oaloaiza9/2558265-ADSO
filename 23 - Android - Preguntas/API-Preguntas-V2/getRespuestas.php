<?php 
    include 'Conexion.php';

    if (!empty($_POST['id_cuestionario']) || !empty($_GET['id_cuestionario'])) {
        $id_cuestionario = (!empty($_POST['id_cuestionario']))? $_POST['id_cuestionario'] : $_GET['id_cuestionario'];

        try {
            $consulta = $base_de_datos->prepare("SELECT preguntas.*, respuestas.respuesta, respuestas.estado FROM preguntas INNER JOIN respuestas ON preguntas.id = respuestas.id_pregunta WHERE respuestas.id_cuestionario = :idc");
            $consulta->bindParam(':idc', $id_cuestionario);
            $consulta->execute();
            $allPreguntas = $consulta->fetchAll(PDO::FETCH_ASSOC);

            $data = [];
            foreach ($allPreguntas as $key => $value) {
                $pregunta = $value;
                $opciones = [];
                if ($pregunta) {
                    $consultaOpciones = $base_de_datos->prepare("SELECT * FROM opciones WHERE id_pregunta = :idp");
                    $consultaOpciones->bindParam(':idp', $pregunta["id"]);
                    $consultaOpciones->execute();
                    $opcionesAll = $consultaOpciones->fetchAll(PDO::FETCH_ASSOC);
                    $opciones = mb_convert_encoding($opcionesAll, "UTF-8", "iso-8859-1");
                }

                $temp = [
                            "pregunta" => mb_convert_encoding($pregunta, "UTF-8", "iso-8859-1"),
                            "opciones" => $opciones
                        ];
                array_push($data, $temp);
            }


            $respuesta = [
                            'status' => true,
                            'respuestas' => $data
                          ];

            echo json_encode($respuesta);
            
        } catch (Exception $e) {
            $respuesta = [
                            'status' => false,
                            'message' => "ERROR##SQL",
                            'exception' => $e
                          ];
            echo json_encode($respuesta);
        }
    }else{
        $respuesta = [
                        'status' => false,
                        'message' => "ERROR##DATOS##POST"
                      ];
        echo json_encode($respuesta);
    }
?>
