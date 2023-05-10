class CrtlUsuarios{
    editCliente (id) {
        //Show selected cliente in edit form
        this.model.errMsgs=[];
        //Get cliente from server and update local model
        this.clientesDAO.busca(id)
            .then(cliente=>{
                this.model.cliente=cliente;
                this.frmEditShow();
            })
            .catch( errores => {
                console.log(errores);
                this.model.errMsgs=errores;
                this.showServerErrors();
            });
    }
    deleteCliente (id=0) {
        this.clientesDAO.borra(id)
            .then(() => {
                this.frmEditHide();
                this.loadClientes();
            })
            .catch(errores  => {
                console.log(errores);
                this.model.errMsgs=errores;
                this.showServerErrors();
            });
    } frmEditUpdate () {
        //Fill form controls with this.model.cliente data
        let c=this.model.cliente;
        $('#id').text( c.id );
        $('[name=nombre]').val(c.nombre);
        $('[name=dni]').val(c.dni);
    }
    frmEditShow () {
        //Shows Edit form
        this.frmEditUpdate();
        this.showServerErrors();
        $(this.config.dialog).modal('show');
    }
    frmEditHide () {
        //Hides Edit form
        $(this.config.dialog).modal('hide');
        //clean previous errors
        $(this.config.errMsgs).empty();
        $('#errNombre').hide();
        $('#errDni').hide();
    }
    showServerErrors () {
        //Show BeanValidation errors from server-side
        console.log(this.model.errMsgs);
        let errorRows = "";
        this.model.errMsgs.forEach( m => {
            errorRows += "<li class='text-danger'>" + m.message + "</li>";
        });
        $(this.config.errMsgs).html(errorRows);

        this.model.errMsgs=[]; //Clean server errors
    }
    loadClientes () {
        //Get clientes from server and update local model
        this.clientesDAO.buscaTodos()
            .then( clientes => {
                this.model.clientes = clientes;
                this.showClientes(); //force view update
            })
            .catch( errores => {
                console.log(errores);
                this.model.errMsgs=errores;
                this.showServerErrors();
            });
    }
    showClientes () {
        //Fill table with clientes information
        let clientesRows = "";
        this.model.clientes.forEach( c => {
            //Place cliente id in user-defined row attribute for easy access
            //(see table click event)
            clientesRows += "<tr data-cliente-id='"+c.id+"'>";
            clientesRows += "<td>" + c.id + "</td>";
            clientesRows += "<td>" + c.nombre + "</td>";
            clientesRows += "<td>" + c.dir + "</td>";
            clientesRows += "</tr>";
        });
        $(this.config.wrapper).html(clientesRows);
    }

}