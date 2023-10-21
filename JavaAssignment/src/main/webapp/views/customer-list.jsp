<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer List</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;400;600&display=swap');

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            text-decoration: none;
            font-family: 'Poppins', sans-serif;
        }

        .app {
            height: 100vh;
            width: 100vw;
            max-width: 100vw;
            max-height: 100vh;
            background-color: white;
        }

        .header {
            width: 100%;
            height: 50px;
            padding: 1em;
            background-color: rgb(226, 226, 226);
        }

        .title .page-title {
            font-weight: 600;
        }

        .list-container {
            width: 100%;
        }

        .list-header {
            width: 100%;
            height: 50px;
            position: relative;
            background-color: aquamarine;
        }

        .addCust-btn {
            position: absolute;
            top: 50%;
            left: 10px;
            transform: translateY(-50%);
            background-color: rgb(0, 81, 255);
            color: white;
            border-radius: 5px;
            border: none;
            height: 30px;
            padding: 5px;
            cursor: pointer; /* Add cursor style to indicate it's clickable */
        }

        .list-title {
            height: 40px;
            width: 150px;
            position: absolute;
            top: 50%;
            left: 50%;
            color: black;
            text-align: center;
            font-size: 20px;
            font-weight: 600;
            transform: translate(-50%, -50%);
        }

        table {
            width: 100%;
        }

        th, td {
            text-align: left;
            border-bottom: 1px solid black;
        }

        td {
            font-size: 14px;
        }

        tbody tr:nth-child(even) {
            background-color: darkgrey;
        }

        /* Style the delete and update icons */
        .action-icons a {
            margin-right: 10px;
        }
    </style>
</head>
<body>
<div class="app">
    <div class="header">
        <div class="title">
            <span class="page-title">Customer List</span>
        </div>
    </div>

    <div class="list-container">
        <div class="list-header">
            <a href="<c:url value='/add-customer.jsp'/>" class="addCust-btn">Add Customer</a>
            <span class="list-title">Customer List</span>
        </div>
        <div class="list-table">
            <table>
                <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${customers}" var="customer">
                    <tr>
                        <td>${customer.firstName}</td>
                        <td>${customer.lastName}</td>
                        <td>${customer.address}</td>
                        <td>${customer.city}</td>
                        <td>${customer.state}</td>
                        <td>${customer.email}</td>
                        <td>${customer.phone}</td>
                        <td class="action-icons">
                            <a href="<c:url value='/deleteCustomer/${customer.customerId}'/">
                                <img src="../images/delete.png" alt="Delete">
                            </a>
                            <a href="<c:url value='/updateCustomer/${customer.customerId}'/">
                                <img src="../images/pencil.png" alt="Update">
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
