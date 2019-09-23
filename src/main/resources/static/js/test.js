$(document).ready(function () {
    var t = $('#asd').DataTable({
        "columnDefs": [{
                "searchable": false,
                "orderable": false,
                "targets": 0
            }],
        "order": [[1, 'asc']]
    });

    t.on('order.dt search.dt', function () {
        t.column(0, {search: 'applied', order: 'applied'}).nodes().each(function (cell, i) {
            cell.innerHTML = i + 1;
        });
    }).draw();
});


function myFunction(id, firstName, lastName, department) {
    if (id.length > 0) {
        document.getElementById("id").readOnly = true;
    } else {
        document.getElementById("id").readOnly = false;
    }
    document.getElementById("id").value = id;
    document.getElementById("firstName").value = firstName;
    document.getElementById("lastName").value = lastName;
    document.getElementById("department").value = department;
}
function sweetAlert22(status1, status2, status3) {
    if (status1.length > 1) {
        swal(status1, status2, status3);
    }
}


$("#menu-toggle").click(function (e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
});