<?php 
    include 'Conexion.php';

    if ((!empty($_POST['id_usuario']) && !empty($_POST['fecha_inicio'])) || (!empty($_GET['id_usuario']) && !empty($_GET['fecha_inicio']))) {

        $id_usuario = (!empty($_POST['id_usuario']))? $_POST['id_usuario'] : $_GET['id_usuario'];
        $fecha_inicio = (!empty($_POST['fecha_inicio']))? $_POST['fecha_inicio'] : $_GET['fecha_inicio'];
        
        try {
            $consulta = $base_de_datos->prepare("INSERT INTO cuestionarios (id_usuario, cant_preguntas, cant_ok, cant_error, fecha_inicio, fecha_fin) VALUES(:idu, 0, 0, 0, :fin, null) ");

            $consulta->bindParam(':idu', $id_usuario);
            $consulta->bindParam(':fin', $fecha_inicio);
            
            $proceso = $consulta->execute();

            if( $proceso ){
                $respuesta = [
                                'status' => true,
                                'mesagge' => "OK##CUESTIONARIO##INSERT",
                                'id_cuestionario' => $id_cuestionario = $base_de_datos->lastInsertId()
                              ];
                echo json_encode($respuesta);
            }else{
                $respuesta = [
                                'status' => false,
                                'mesagge' => "ERROR##CUESTIONARIO##INSERT"
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
