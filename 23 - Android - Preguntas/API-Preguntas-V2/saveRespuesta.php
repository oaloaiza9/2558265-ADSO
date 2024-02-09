<?php 
    include 'Conexion.php';

    if ((!empty($_POST['id_cuestionario']) && !empty($_POST['id_pregunta']) && !empty($_POST['respuesta']) && !empty($_POST['estado'])) || (!empty($_GET['id_cuestionario']) && !empty($_GET['id_pregunta']) && !empty($_GET['respuesta']) && !empty($_GET['estado']))) {

        $id_cuestionario = (!empty($_POST['id_cuestionario']))? $_POST['id_cuestionario'] : $_GET['id_cuestionario'];
        $id_pregunta = (!empty($_POST['id_pregunta']))? $_POST['id_pregunta'] : $_GET['id_pregunta'];
        $respuesta = (!empty($_POST['respuesta']))? $_POST['respuesta'] : $_GET['respuesta'];
        $respuesta = mb_convert_encoding($respuesta, "UTF-8", "iso-8859-1");
        $estado = (!empty($_POST['estado']))? $_POST['estado'] : $_GET['estado'];


        try {
            $consulta = $base_de_datos->prepare("INSERT INTO respuestas (id_cuestionario, id_pregunta, respuesta, estado) VALUES(:idc, :idp, :res, :val) ");

            $consulta->bindParam(':idc', $id_cuestionario);
            $consulta->bindParam(':idp', $id_pregunta);
            $consulta->bindParam(':res', $respuesta);
            $consulta->bindParam(':val', $estado);
            
            $proceso = $consulta->execute();

            if( $proceso ){
                $respuesta = [
                                'status' => true,
                                'mesagge' => "OK##RESPUESTA##INSERT",
                                'POST' => $_POST,
                                'respuesta' => $respuesta
                              ];
                echo json_encode($respuesta);
            }else{
                $respuesta = [
                                'status' => false,
                                'mesagge' => "ERROR##RESPUESTA##INSERT"
                              ];
                echo json_encode($respuesta);
            }
        } catch (Exception $e) {
            $respuesta = [
                            'status' => false,
                            'mesagge' => "ERROR##SQL",
                            'exception' => $e
                          ];
            echo json_encode($respuesta);
        }
    }else{
        $respuesta = [
                        'status' => false,
                        'mesagge' => "ERROR##DATOS##POST"
                      ];
        echo json_encode($respuesta);
    }
?>
