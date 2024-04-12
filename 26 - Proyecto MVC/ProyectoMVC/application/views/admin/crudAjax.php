<?php 
  $dataHeader['titulo'] = "Crear Usuarios";
  $this->load->view('layouts/header', $dataHeader); 
?>
  <?php 
    $dataSidebar['session'] = $session;
    $dataSidebar['optionSelected'] = 'openCrudAjax';
    $this->load->view('layouts/sidebar', $dataSidebar); 
  ?>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <div class="col-12 m-0 p-4 bg-white">
      <div class="row justify-content-end px-2 pb-2">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalCrearUsuario"><b>CREAR USUARIO</b></button>
      </div>
      <table class="table table-bordered">
        <thead>
          <tr class="bg-dark text-light">
            <th class="text-center">CEDULA</th>
            <th class="text-center">NOMBRES</th>
            <th class="text-center">APELLIDOS</th>
            <th class="text-center">TELEFONO</th>
            <th class="text-center">DIRECCION</th>
            <th class="text-center">CORREO</th>
          </tr>
        </thead>
        <tbody id="tbodyPersonas">
          
        </tbody>
      </table> 
    </div>
  </div>

  <div class="modal fade" id="modalCrearUsuario">
    <div class="modal-dialog">
      <form id="formularioCrearUsuario" action="#" method="POST">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">CREAR USUARIO</h4>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="row justify-content-center">
              <div class="row mb-3">
                <div class="col">
                  <label for="new_cedula" class="form-label">
                    <i class="fas fa-id-card"></i> Cédula
                  </label>
                  <input type="number" class="form-control" id="campo_cedula" name="campo_cedula" value="">
                </div>
                <div class="col">
                  <label for="new_nombres" class="form-label">
                    <i class="fas fa-user"></i> Nombres
                  </label>
                  <input type="text" class="form-control" id="campo_nombres" name="campo_nombres" value="">
                </div>
              </div>

              <div class="row mb-3">
                <div class="col">
                  <label for="new_apellidos" class="form-label">
                    <i class="fas fa-user"></i> Apellidos
                  </label>
                  <input type="text" class="form-control" id="campo_apellidos" name="campo_apellidos" value="">
                </div>
                <div class="col">
                  <label for="new_telefono" class="form-label">
                    <i class="fas fa-phone"></i> Teléfono
                  </label>
                  <input type="tel" class="form-control" id="campo_telefono" name="campo_telefono" value="">
                </div>
              </div>

              <div class="col row px-4 mb-3">
                <div class="col">
                  <label for="new_direccion" class="form-label">
                    <i class="fas fa-map-marker-alt"></i> Dirección
                  </label>
                  <input type="text" class="form-control" id="campo_direccion" name="campo_direccion" value="">
                </div>
              </div>

              <div class="row mb-3">
                <div class="col">
                  <label for="new_correo" class="form-label">
                    <i class="fas fa-envelope"></i> Correo
                  </label>
                  <input type="email" class="form-control" id="campo_email" name="campo_email" value="">
                </div>
                <div class="col">
                  <label for="new_password" class="form-label">
                    <i class="fas fa-lock"></i> Password
                  </label>
                  <input type="password" class="form-control" id="campo_password" name="campo_password" value="">
                </div>
              </div>

              <div class="col-12 row px-4 mb-3">
                <div class="col-6">
                  <label for="new_tipo" class="form-label">
                    <i class="fas fa-user"></i> Tipo
                  </label>
                  <select class="form-control" id="campo_tipo" name="campo_tipo" value="ADMIN">
                    <option value="VENDEDOR">VENDEDOR</option>
                    <option value="ADMIN">ADMINISTRADOR</option>
                  </select>
                </div>
              </div>
            </div>

          </div>
          <div class="modal-footer justify-content-center">
            <button type="button" class="btn btn-default" data-dismiss="modal">CERRAR</button>
            <button type="submit" class="btn btn-primary">REGISTRAR</button>
          </div>
        </div>
        <!-- /.modal-content -->
      </form>
    </div>
    <!-- /.modal-dialog -->
  </div>
  <script src="<?= base_url('dist/js/my_script.js') ?>"></script>
<?php 
  $this->load->view('layouts/footer'); 
?>