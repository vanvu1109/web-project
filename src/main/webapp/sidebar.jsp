<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark" style="width: 280px;">
    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
      <span class="fs-4 ms-4">Quản lý thư viện</span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
<%--       <li class="nav-item">
        <a href="${pageContext.request.contextPath}/dashboard" class="nav-link <%= "dashboard".equals(request.getAttribute("activePage")) ? "active" : "text-white" %>" aria-current="page">
          <i class="bi bi-bar-chart me-2"></i>
          Thống kê
        </a>
      </li> --%>
      <li class="nav-item">
        <a href="${pageContext.request.contextPath}/borrow" class="nav-link <%= "borrow".equals(request.getAttribute("activePage")) ? "active" : "text-white" %>" aria-current="page">
          <i class="bi bi-file-earmark-plus me-2"></i>
          Cho mượn sách
        </a>
      </li>
      <li>
        <a href="${pageContext.request.contextPath}/borrowings" class="nav-link <%= "borrowings".equals(request.getAttribute("activePage")) ? "active" : "text-white" %>">
          <i class="bi bi-file-earmark-text me-2"></i>
          Phiếu mượn
        </a>
      </li>
      <li>
        <a href="${pageContext.request.contextPath}/readers" class="nav-link <%= "readers".equals(request.getAttribute("activePage")) ? "active" : "text-white" %>">
          <i class="bi bi-person-check me-2"></i>
          Thành viên
        </a>
      </li>
      <li>
        <a href="${pageContext.request.contextPath}/books" class="nav-link <%= "books".equals(request.getAttribute("activePage")) ? "active" : "text-white" %>">
          <i class="bi bi-book me-2"></i>
          Sách
        </a>
      </li>
      <li>
        <a href="${pageContext.request.contextPath}/categories" class="nav-link <%= "categories".equals(request.getAttribute("activePage")) ? "active" : "text-white" %>">
          <i class="bi bi-bookmark me-2"></i>
          Thể loại
        </a>
      </li>
      <li>
        <a href="${pageContext.request.contextPath}/users" class="nav-link <%= "users".equals(request.getAttribute("activePage")) ? "active" : "text-white" %>">
          <i class="bi bi-people  me-2"></i>
          Nhân viên
        </a>
      </li>
    </ul>
  </div>