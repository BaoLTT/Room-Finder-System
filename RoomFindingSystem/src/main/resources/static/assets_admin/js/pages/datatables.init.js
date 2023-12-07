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
    }).buttons().container().appendTo("#datatable-buttons_wrapper .col-md-6:eq(0)"),

        $("#datatable-buttons").DataTable({
            columnDefs: [
                {
                    orderable: false,
                    targets: 3
                },
                {
                    orderable: false,
                    targets: 7
                }
            ],
            lengthChange: !1,
            buttons: ["excel"]
        }).buttons().container().appendTo("#datatable-buttons_wrapper .col-md-6:eq(0)")
});