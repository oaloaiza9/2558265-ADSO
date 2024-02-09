<?php 
    include 'Conexion.php';

    if (!empty($_POST['id_usuario']) || !empty($_GET['id_usuario'])) {
        $id_usuario = (!empty($_POST['id_usuario']))? $_POST['id_usuario'] : $_GET['id_usuario'];

        try {
            $consulta = $base_de_datos->prepare("SELECT * FROM cuestionarios WHERE id_usuario = :idu ORDER BY fecha_inicio DESC");
            $consulta->bindParam(':idu', $id_usuario);
            $consulta->execute();
            $cuestionarios = $consulta->fetchAll(PDO::FETCH_ASSOC);

            $resumen = [];
            if ($cuestionarios) {
                foreach ($cuestionarios as $key => $value) {
                    $id_cuestionario = $value['id'];
                    $consulta_cant_preguntas = $base_de_datos->prepare("SELECT * FROM respuestas WHERE id_cuestionario = :idc ");
                    $consulta_cant_preguntas->bindParam(':idc', $id_cuestionario);
                    $consulta_cant_preguntas->execute();
                    
                    $preguntas_temp = $consulta_cant_preguntas->fetchAll(PDO::FETCH_ASSOC);
                    $cant_ok_temp = 0;

                    if ($preguntas_temp) {
                        foreach ($preguntas_temp as $key2 => $value2) {
                            if ($value2['estado'] == "OK") {
                                $cant_ok_temp++;
                            }
                        }
                    }

                    $data_temp = [
                                    'id_cuestionario' => $id_cuestionario,
                                    'cant_preguntas' => sizeof($preguntas_temp),
                                    'cant_preguntas_ok' => $cant_ok_temp,
                                    'cant_preguntas_error' => (sizeof($preguntas_temp)-$cant_ok_temp),
                                    'fecha_inicio' => $value['fecha_inicio'],
                                    'fecha_fin' => $value['fecha_fin'],
                                 ];
                    array_push($resumen, $data_temp);
                }
            }

            $respuesta = [
                            'status' => true,
                            'resumen' => $resumen,
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
