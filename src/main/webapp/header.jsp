<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header class="py-3 mb-3 border-bottom">
    <div class="d-flex align-items-center justify-content-between pe-3">
    	<div>
    		<h1 class="fs-4 ms-3 mb-0"><%= request.getAttribute("titlePage") %></h1>
    	</div>
      <div class="flex-shrink-0 dropdown">
        <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
          <img src="https://github.com/mdo.png" alt="mdo" width="32" height="32" class="rounded-circle">
        </a>
        <ul class="dropdown-menu text-small shadow" aria-labelledby="dropdownUser2">
          <li><a class="dropdown-item" href="#">New project...</a></li>
          <li><a class="dropdown-item" href="#">Cài đặt</a></li>
          <li><a class="dropdown-item" href="#">Trang cá nhân</a></li>
          <li><hr class="dropdown-divider"></li>
          <li><a class="dropdown-item" href="#">Danwgd xuất</a></li>
        </ul>
      </div>
    </div>
 </header>