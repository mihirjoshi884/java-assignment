<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Details</title>
</head>
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

        background-color:rgb(226, 226, 226);
    }

    .title .page-title {
        font-weight: 600;
    }

    .form-container {
        height: 300px;
        width: 500px;

        position: relative;
        top: 50%;
        left: 50%;
        transform: translate(-50%,-50%);
    }

    form {
        display: grid;
        grid-template-columns: 50% 50%;
        grid-template-rows: 25% 25% 25% 25%;
        gap: 15px;

    }

    .form-input {
        height: 30px;
        border : 1px solid black;
        padding: 5px;
        border-radius: 3px;

    }
    .form-container button {
        position: absolute;
        bottom: 60px;
        right: -15px;
        height: 30px;

        padding: 5px;

        background-color: rgb(0, 81, 255);
        color: white;

        border: none;
        border-radius: 5px;
    }
</style>
<body>
<div class="app">
    <div class="header">
        <div class="title">
            <span class="page-title">Customer Details</span>
        </div>
    </div>

    <div class="form-container">
        <form action="" id="customerForm" method="POST">
            <input type="text" name="fname" id="fname" class="form-input" placeholder="First Name" style="grid-row: 1/ span 1; grid-column: 1/ span 1">
            <input type="text" name="lname" id="lname" class="form-input" placeholder="Last Name" style="grid-row: 1/ span 1; grid-column: 2/ span 1">
            <input type="text" name="street" id="street" class="form-input" placeholder="Street" style="grid-row: 2/ span 1; grid-column: 1/ span 1">
            <input type="text" name="addr" id="addr" class="form-input" placeholder="Address" style="grid-row: 2/ span 1; grid-column: 2/ span 1">
            <input type="text" name="city" id="city" class="form-input" placeholder="City" style="grid-row: 3/ span 1; grid-column: 1/ span 1">
            <input type="text" name="state" id="state" class="form-input" placeholder="State" style="grid-row: 3/ span 1; grid-column: 2/ span 1">
            <input type="email" name="email" id="email" class="form-input" placeholder="Email" style="grid-row: 4/ span 1; grid-column: 1/ span 1">
            <input type="number" name="phno" id="phno" class="form-input" placeholder="Phone Number" style="grid-row: 4/ span 1; grid-column: 2/ span 1">

        </form>
        <button type="submit" form="customerForm" value="submit">Submit</button>
    </div>
</div>
</body>
</html>
