
window.onload = function() {
    const adminModal = document.getElementById('admin-modal');
    const closeBtn = document.getElementsByClassName("adminClose")[0];

    const adminBtn = document.getElementById("adminBtn");
    adminBtn.onclick = function () {
        console.log('click');
        adminModal.style.display = "block";
    };

    closeBtn.onclick = function() {
        console.log('dsf');
        adminModal.style.display = "none";
    };

    window.onclick = function(event) {
        if (event.target === adminModal) {
            adminModal.style.display = "none";
        }
    };
}

function goToUserPage(){
    window.location.href="/api/admin/user-page";
}

function goToPostPage(){
    window.location.href="/api/admin/post-page";
}

function goToCommentPage() {
    window.location.href="/api/admin/comment-page";
}