function callButtom(){
    swal({ title: "Are you sure?",
     text: "Your will not be able to recover this imaginary file!",
     type: "warning",
    showCancelButton: true,
    confirmButtonClass: "btn-danger",
      confirmButtonText: "Yes, delete it!",
     closeOnConfirm: false
    },
    function(){
        swal("Deleted!", "Your imaginary file has been deleted.", "success");
    });
    return true;
}