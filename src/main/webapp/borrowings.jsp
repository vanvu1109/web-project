<%@page import="model.BorrowingDetails"%>
<%@page import="model.Borrowing"%>
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
						<input class="form-control" placeholder="Tìm kiếm" />
					</div>
					<a href="${pageContext.request.contextPath}/borrow"
						class="btn btn-success"> <i class="bi bi-plus"></i>Thêm
					</a>
				</div>

				<div class="overflow-y-scroll" style="height: 490px">
					<div class="container-fluid px-3">
						<div class="row">
							<div class="col">#</div>
							<div class="col">Thành viên</div>
							<div class="col">Ngày mượn</div>
						</div>
					</div>
					<div class="accordion" id="borrowings">
						<%
						List<Borrowing> borrowings = (List<Borrowing>) request.getAttribute("borrowings");
						if (borrowings != null && !borrowings.isEmpty()) {
							for (Borrowing borrowing : borrowings) {
						%>
						<div class="accordion-item">
							<h2 class="accordion-header">
								<div class="accordion-button" data-bs-toggle="collapse"
									data-bs-target="#details-<%=borrowing.getId()%>"
									aria-controls="details-<%=borrowing.getId()%>">
									<div class="container-fluid">
										<div class="row">
											<div class="col"><%=borrowing.getId()%></div>
											<div class="col"><%=borrowing.getReader().getName()%></div>
											<div class="col"><%=borrowing.getBorrowDate()%></div>
										</div>
									</div>
								</div>
							</h2>
							<div id="details-<%=borrowing.getId()%>"
								class="accordion-collapse collapse" data-bs-parent="#borrowings">
								<div class="accordion-body">
									<h6>Chi tiết phiếu mượn:</h6>
									<div class="container-fluid">
										<div class="row border-bottom bg-body-secondary">
											<div class="col p-1 fw-medium text-uppercase" style="width: 300px">Tên
												sách</div>
											<div class="col p-1 fw-medium text-uppercase" style="width: 300px">Loại
												sách</div>
											<div class="col p-1 fw-medium text-uppercase" style="width: 300px">Trả
												sách</div>
										</div>
									</div>
									<%
									for (BorrowingDetails detail : borrowing.getBorrowingDetails()) {
									%>
									<div class="container-fluid">
										<div class="row border-bottom">
											<div class="col p-1" style="width: 300px"><%=detail.getBook().getName()%></div>
											<div class="col p-1" style="width: 300px">
												<span class="badge bg-primary"><%=detail.getBook().getCategory().getName()%></span>
											</div>
											<div class="col p-1" style="width: 300px">
												<%
												if (detail.getReturnDate() == null) {
												%>
												<form method="POST"
													action="${pageContext.request.contextPath }/return-book">
													<input class="d-none" name="borrowingDetailsId"
														value="<%=detail.getId()%>">
													<button class="btn btn-primary btn-sm">Nhận sách</button>
												</form>
												<%
												} else {
												%>
												<span> <%=detail.getReturnDate()%>
												</span>
												<%
												}
												%>
											</div>
										</div>
									</div>
									<%
									}
									%>
								</div>
							</div>
						</div>
						<%
						}
						} else {
						%>
						<div>
							<p class="text-center">Không có thể loại nào</p>
						</div>
						<%
						}
						%>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="footer.jsp"%>