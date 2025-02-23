<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transfer </title>
</head>
 <div class="container mt-5">
        <h2>Transfer Money</h2>
        <form action="transfer" method="post">
            <input type="hidden" name="fromUserId" value="1"> <!-- Replace with dynamic user ID -->
            <div class="mb-3">
                <label for="toUserId" class="form-label">Recipient User ID</label>
                <input type="number" class="form-control" id="toUserId" name="toUserId" placeholder="Enter recipient user ID" required>
            </div>
            <div class="mb-3">
                <label for="amount" class="form-label">Amount</label>
                <input type="number" class="form-control" id="amount" name="amount" placeholder="Enter amount" required>
            </div>
            <button type="submit" class="btn btn-primary">Transfer</button>
        </form>
    </div>
</html>