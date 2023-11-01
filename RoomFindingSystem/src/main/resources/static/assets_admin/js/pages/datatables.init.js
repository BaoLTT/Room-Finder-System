$(document).ready(function () {
    // Khởi tạo DataTable cho bảng có id "datatable"
    $("#datatable").DataTable();
  
    // Khởi tạo DataTable cho bảng có id "datatable-buttons" và cấu hình các nút
    $("#datatable-buttons").DataTable({
      lengthChange: false,
      buttons: ["copy", "excel", "pdf"],
    });
  
    // Di chuyển nút xuất và sao chép vào vị trí mong muốn
    $("#datatable-buttons_wrapper .col-md-6:eq(0)").append(
      $("#datatable-buttons_wrapper .dt-buttons")
    );
  });
  