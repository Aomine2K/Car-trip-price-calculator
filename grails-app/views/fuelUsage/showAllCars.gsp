<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>

<table>
    <g:each in="${carList}" var="car">
        <tr>
            <td>${car.id}</td>
            <td>${car.model.name}</td>
            <td>${car.producer.name}</td>
            <td>${car.fuelConsumption}</td>
        </tr>
    </g:each>
</table>

</body>
</html>