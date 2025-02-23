<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div class="container mt-5">
        <h2>Balance</h2>
        
         <!-- Balance Check Form -->
        <h1 class="text-center mb-4">Check Balance</h1>
        <div class="row justify-content-center">
            <div class="col-md-6">
                <form action="transaction" method="GET">
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input type="text" id="username" name="username" class="form-control" required>
                    </div>

                    <button type="submit" class="btn btn-info btn-block">Check Balance</button>
                </form>
            </div>
        </div>
     <a href="dashboard.jsp" class="btn btn-primary">Back to Dashboard</a>
    </div>
</body>
</html>