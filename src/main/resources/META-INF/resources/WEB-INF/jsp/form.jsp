<html>
<body>
<center>
    <h1>XML validation against XSD</h1>

    <form method="POST" action="/validate/form" enctype="multipart/form-data">
        <b>xml: </b></b><input type="file" name="xmlFile"/><br/> <b>xsd:
    </b><input type="file" name="xsdFile"/><br/> <br/> <input
            type="submit" value="Validate"/>
    </form>

    Validation result: ${message}
</center>

</body>
</html>
