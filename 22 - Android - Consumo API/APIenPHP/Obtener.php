<?php 
	header("Content-Type: application/json");
	header("Access-Control-Allow-Origin: * "); // Permite el acceso desde cualquier origen, o usa "http://localhost" si solo quieres permitirlo desde localhost.
    header("Access-Control-Allow-Methods: GET, POST");
	header("Access-Control-Allow-Headers: Content-Type");

    include 'Conexion.php';

    $consulta = $base_de_datos->query("SELECT * FROM personas");
    $datos = $consulta->fetchAll(PDO::FETCH_ASSOC);

    // Codifica los datos en UTF-8, para que se puedan convertir a Json sin problema (Ñ y tildes)
    $datos = mb_convert_encoding($datos, "UTF-8", "iso-8859-1");

    $respuesta['registros'] = $datos;
    echo json_encode($respuesta);
?>

