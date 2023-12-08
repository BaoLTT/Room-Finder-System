$(document).ready(function () {
    $("#datatable-users").DataTable({
        columnDefs: [
            {
                orderable: false,
                targets: 1
            },
            {
                orderable: false,
                targets: 2
            },
            {
                orderable: false,
                targets: 6
            }
        ],
        lengthChange: !1
    }).buttons().container().appendTo("#datatable-users_wrapper .col-md-6:eq(0)"),

        $("#datatable-rooms").DataTable({
            lengthChange: !1,
            info: false,
            buttons: ["excel"]
        }).buttons().container().appendTo("#datatable-rooms_wrapper .col-md-6:eq(0)"),

        $("#datatable-buttons").DataTable({
            lengthChange: !1,
            buttons: ["excel"]
        }).buttons().container().appendTo("#datatable-buttons_wrapper .col-md-6:eq(0)")
});