function RemoveFavoriteItem(productId) {
    $.ajax({
        url: 'removeFavorite',
        data: {
            productId: productId
        },
        success: function (responseText) {
            switch (responseText) {
                case "Remove success":
                    showNotification("success", "Remove product successful");
                    setTimeout(() => {
                        window.location.reload();
                    }, 3000)
                    break;
                case "Remove faild":
                    showNotification("error", "Error when remove product from your favorite");
                    break;
                default:
                    alert("Please sign in to use this function....");
                    break;
            }
        }
    });
}
function AddToFavorite(id) {
    $.ajax({
        url: 'http://localhost:8080/add-favourite-list',
        data: {
            id: id
        },
        success: function (model) {
         showNotification("success", "Add product to favorite successful");
            switch (model) {
                case "Add successful":
                    showNotification("success", "Add product to favorite successful");
                    break;
                case "Add faild":
                    showNotification("error", "Error when add product to favorite");
                    break;
                case "Sign in first":
                    alert("Please sign in to use this function....");
                    break;

                default:

                    break;
            }
        }
    });
}



 function showSuccessToast() {
     showNotification("warning", "Đã xoá khỏi Danh Sách yêu Thích!");
   }
   function showSuccessToastAdd() {
        showNotification("success", "Đã Thêm Vào Danh Sách Yêu Thích!");
      }

  function showErrorToast() {
    toast({
      title: "Thất bại!",
      message: "Có lỗi xảy ra, vui lòng liên hệ quản trị viên.",
      type: "error",
      duration: 5000
    });
  }
const noti_box = document.querySelector("#notification-box");
function showNotification(type, message) {
    let noti = document.createElement("div");
    noti.className = `notification ${type}`;
    noti.innerHTML = `
    <i class="fa-solid fa-xmark close"></i>
    <div class="notification__title">
      <i class="fa fa-trash"></i>
      <span>${type.charAt(0).toUpperCase() + type.slice(1)}</span>
    </div>
    <div class="notification__detail">
      ${message}
    </div>
    `;

    const notiClose = document.querySelectorAll(".close");
    notiClose.forEach(item => {
        item.addEventListener("click", e => {
            item.parentElement.remove();
        });
    });
    noti_box.appendChild(noti)
    setTimeout(() => {
        noti.style.animation = "disappear 1s linear forwards";
    }, 2000);
    setTimeout(() => {
        noti.remove()
    }, 4000);
}
