<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta content="Responsive bootstrap 4 admin template" name="description">
<meta content="Coderthemes" name="author">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- App favicon -->
<link rel="shortcut icon" href="/admin/assets\images\favicon.ico">

<c:if test="${param.page =='create'}">
    <link href="/admin/assets\libs\toastr\toastr.min.css" rel="stylesheet" type="text/css">
</c:if>
<!-- App css -->
<link href="/admin/assets\css\bootstrap.min.css" rel="stylesheet" type="text/css" id="bootstrap-stylesheet">
<link href="/admin/assets\css\icons.min.css" rel="stylesheet" type="text/css">
<link href="/admin/assets\css\app.min.css" rel="stylesheet" type="text/css" id="app-stylesheet">
