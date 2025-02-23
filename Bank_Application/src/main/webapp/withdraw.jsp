<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Withdraw Funds</title>

    <!-- Bootstrap CSS (via CDN) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy90fLRuv7jALs0H8BdB2tP0yYl2gP0sKz0jwImM" crossorigin="anonymous">

    <!-- Custom CSS -->
    <style>
        body {
            background-color: #f4f6f9;
            font-family: 'Arial', sans-serif;
        }
        
        .container {
            max-width: 600px;
            margin-top: 50px;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .alert-dismissible .btn-close {
            position: absolute;
            top: 10px;
            right: 10px;
        }

        .form-label {
            font-weight: bold;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }

        .alert-info {
            background-color: #d1ecf1;
            color: #0c5460;
        }
        
        .alert-danger {
            background-color: #f8d7da;
            color: #721c24;
        }
        
        .alert-success {
            background-color: #d4edda;
            color: #155724;
        }
    </style>
</head>
<body>

    <!-- Main container for withdrawal page -->
    <div class="container">

        <!-- Page Header -->
        <h2 class="text-center mb-4">Withdraw Funds</h2>

        <!-- Error Message Display -->g
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <strong>Error:</strong> ${errorMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

        <!-- Success Message Display -->
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <strong>Success:</strong> ${successMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

        <!-- Withdrawal Form -->
        <form action="withdraw" method="post">
            <div class="mb-3">
                <label for="amount" class="form-label">Enter Withdrawal Amount:</label>
                <input type="number" id="amount" name="amount" class="form-control" required min="0.01" step="0.01" placeholder="Amount to withdraw" />
            </div>

            <button type="submit" class="btn btn-primary w-100">Withdraw</button>
        </form>

        <!-- Display current balance -->
        <c:if test="${not empty currentBalance}">
            <div class="mt-4 alert alert-info" role="alert">
                <strong>Your current balance is:</strong> $${currentBalance}
            </div>
        </c:if>

    </div>

    <!-- Bootstrap JS (via CDN) and Popper.js for tooltips and dropdowns -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz4fnFO9gyb2R6jTVHqrXc86Y0E+SgP0yBd4Y9HgOx2g3bXlmcfOd9tL2p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0v8Fq8pS5yzk4l+fnwHvJJf6lQF7pX7vIBr4MO5m0iU5QAnb" crossorigin="anonymous"></script>

</body>
</html>
