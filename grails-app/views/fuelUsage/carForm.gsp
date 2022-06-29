<%--
  Created by IntelliJ IDEA.
  User: Kacper
  Date: 08.06.2022
  Time: 18:50
--%>

<html>
<head>
    <title></title>
    <meta name="layout" content="main"/>
</head>

<body>

<g:message code="car.create.new.headline"/>

<g:form name="myForm" url="[controller: 'fuelUsage', action: 'saveNewCar']">
    <label><g:message code="car.producer.label"/> :</label>
    <g:select name="producer" from="${listOfProducers}"/>
    <label>Model:</label> <g:textField name="model"/><br/>
    <label>Rodzaj paliwa:</label> <g:textField name="fuelTypeMyszjolen"/><br/>
    <label>Pojemność silnika:</label> <g:textField name="engineVolume"/><br/>
    <label>Zużycie paliwa:</label> <g:textField name="fuelConsumption"/><br/>
    <g:submitButton name="Zapisz">Zapisz</g:submitButton>
</g:form>

</body>
</html>