(function($) {
    "use strict";
  
    var ChartHandler = function () {};
  
    ChartHandler.prototype.respChart = function (canvas, chartType, data, options) {
        var ctx = canvas.get(0).getContext("2d");
        var parent = $(canvas).parent();
  
        function adjustCanvasSize() {
            canvas.attr("width", parent.width());
            switch (chartType) {
                case "Line":
                    new Chart(ctx, { type: "line", data: data, options: options });
                    break;
                case "Doughnut":
                    new Chart(ctx, { type: "doughnut", data: data, options: options });
                    break;
                case "Pie":
                    new Chart(ctx, { type: "pie", data: data, options: options });
                    break;
                case "Bar":
                    new Chart(ctx, { type: "bar", data: data, options: options });
                    break;
                case "Radar":
                    new Chart(ctx, { type: "radar", data: data, options: options });
                    break;
                case "PolarArea":
                    new Chart(ctx, { type: "polarArea", data: data, options: options });
                    break;
            }
        }
  
        $(window).resize(adjustCanvasSize);
        adjustCanvasSize();
    };
  
    ChartHandler.prototype.init = function () {
        this.respChart($("#pie1"), "Pie", {
            labels: ["Đang xử lý", "Đã xử lý"],
            datasets: [{
                data: [80, 50],
                backgroundColor: ["#5d6dc3", "#3ec396"],
                hoverBackgroundColor: ["#5d6dc3", "#3ec396"],
                hoverBorderColor: "#fff"
            }]
        });
  
        this.respChart($("#bar"), "Bar", {
            labels: ["January", "February", "March", "April", "May", "June", "July"],
            datasets: [{
                label: "Số bài đăng",
                backgroundColor: "rgba(60, 134, 216, 0.3",
                borderColor: "#3c86d8",
                borderWidth: 2,
                hoverBackgroundColor: "rgba(60, 134, 216, 0.7)",
                hoverBorderColor: "#3c86d8",
                data: [65, 59, 80, 81, 56, 55, 40, 20]
            }]
        });
    };
  
    $.ChartJs = new ChartHandler;
    $.ChartJs.Constructor = ChartHandler;
  
    // Gọi hàm init của ChartJs
    $.ChartJs.init();
  
    // Khởi tạo DataTable cho bảng có id "datatable"
    $("#datatable").DataTable();
  
  
    // Di chuyển nút xuất và sao chép vào vị trí mong muốn
    $("#datatable-buttons_wrapper .col-md-6:eq(0)").append(
      $("#datatable-buttons_wrapper .dt-buttons")
    );
  
  })(window.jQuery);
  