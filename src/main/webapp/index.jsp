<!DOCTYPE html>
<html>
<head>
    <title>Student Management</title>
</head>
<body>
    <h1>Welcome to Student Management</h1>
    <form action="save" method="post" enctype="multipart/form-data">
        Name: <input type="text" name="name" required><br><br>
        Age: <input type="text" name="age" required><br><br>
        
        Photo: <input type="file" name="photo" required><br><br>
        <input type="submit" value="submit">
    </form>
</body>
</html>
