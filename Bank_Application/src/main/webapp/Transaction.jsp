<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transaction</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <!-- Transaction Form -->
        <h1 class="text-center mb-4">Transaction Form</h1>
        <div class="row justify-content-center">
            <div class="col-md-6">
                <form action="TransactionServlet" method="POST">
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input type="text" id="username" name="username" class="form-control" required>
                    </div>

                    <div class="form-group">
                        <label for="transactionType">Transaction Type (deposit/withdraw):</label>
                        <input type="text" id="transactionType" name="transactionType" class="form-control" required>
                    </div>

                    <div class="form-group">
                        <label for="amount">Amount:</label>
                        <input type="number" id="amount" name="amount" class="form-control" required>
                    </div>

                    <button type="submit" class="btn btn-primary btn-block">Submit Transaction</button>
                </form>
            </div>
        </div>

        <hr class="my-4">

       

        <hr class="my-4">

        <!-- Transaction and Balance Status Messages -->
        <c:if test="${not empty requestScope.balance}">
            <div class="alert alert-success">
                <h4 class="alert-heading">User Balance:</h4>
                <p>${requestScope.balance}</p>
            </div>
        </c:if>

        <c:if test="${not empty requestScope.transactionStatus}">
            <div class="alert alert-info">
                <h4 class="alert-heading">Transaction Status:</h4>
                <p>${requestScope.transactionStatus}</p>
            </div>
        </c:if>
    </div>

    <!-- Bootstrap JS and dependencies (optional for full Bootstrap functionality like modals, tooltips, etc.) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
