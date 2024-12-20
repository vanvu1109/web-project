<%@page import="model.Reader"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<body>
	<div class="d-flex" style="height: 100vh">
		<%@ include file="sidebar.jsp"%>
		<div class="flex-grow-1">
			<%@ include file="header.jsp"%>
			<div class="border rounded m-4 p-4">
				<div class="d-flex justify-content-between mb-3">
                    <div>
                        <input class="form-control" placeholder="Tìm kiếm"/>
                    </div>
                    <%@ include file="add_reader.jsp"%>
                </div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>ID</th>
							<th>CMND/CCCD</th>
							<th>Tên</th>
							<!-- <th>Ngày sinh</th> -->
							<th>Email</th>
							<th>Số điện thoại</th>
							<th>Địa chỉ</th>
							<th>Ngày bắt đầu</th>
							<th>Ngày kết thúc</th>
							<th>Thao tác</th>
						</tr>
					</thead>
					<tbody>
						<%
						List<Reader> readers = (List<Reader>) request.getAttribute("readers");
						if (readers != null && !readers.isEmpty()) {
							for (Reader reader : readers) {
						%>
						<tr>
							<td><%=reader.getId()%></td>
							<td><%=reader.getIdentityCard()%></td>
							<td><%=reader.getName()%></td>
							<%-- <td><%=reader.getDob()%></td> --%>
							<td><%=reader.getEmail()%></td>
							<td><%=reader.getPhone()%></td>
							<td><%=reader.getAddress()%></td>
							<td><%=reader.getStartDate()%></td>
							<td><%=reader.getEndDate()%></td>
							<td>
                                 <button class="btn btn-primary">
                                     <i class="bi bi-pencil-square"></i>
                                 </button>
                                 <button class="btn btn-danger">
                                     <i class="bi bi-trash"></i>
                                 </button>
                             </td>
						</tr>
						<%
						}
						} else {
						%>
						<tr>
							<td colspan="9" class="text-center">Không có dữ liệu</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>

		</div>
	</div>
</body>
<%@ include file="footer.jsp"%>