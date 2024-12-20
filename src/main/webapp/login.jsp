<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<body>
<div class="container">
    <div class="row">
      <div class="col-md-6 offset-md-3">
        <h2 class="text-center text-dark mt-5">Quản lý thư viện</h2>
        <div class="text-center mb-5 text-dark">Đăng nhập</div>
        <div class="card my-5">
          <form class="card-body cardbody-color p-lg-5" method="POST" action="${pageContext.request.contextPath}/login">
            <div class="text-center">
              <img src="https://cdn.pixabay.com/photo/2016/03/31/19/56/avatar-1295397__340.png" class="img-fluid profile-image-pic img-thumbnail rounded-circle my-3"
                width="200px" alt="profile">
            </div>

            <div class="mb-3">
              <input type="text" class="form-control" id="Username" aria-describedby="emailHelp"
                placeholder="Tên tài khoản">
            </div>
            <div class="mb-3">
              <input type="password" class="form-control" id="password" placeholder="Mật khẩu">
            </div>
            <div class="text-center"><button type="submit" class="btn btn-primary px-5 mb-5 w-100">Đăng nhập</button></div>
            </div>
          </form>
        </div>

      </div>
    </div>
  </div>
</body>
<%@ include file="footer.jsp" %>