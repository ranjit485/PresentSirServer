<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!doctype html>

<html
  lang="en"
  class="light-style layout-wide customizer-hide"
  dir="ltr"
  data-theme="theme-default"
  data-assets-path="${pagecontext.request.contextpath}/resources/"
  data-template="vertical-menu-template-free"
  data-style="light">
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />

    <title>Register</title>

    <meta name="description" content="" />

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="${pagecontext.request.contextpath}/resources/img/favicon/favicon.ico" />

    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
      rel="stylesheet" />

    <link rel="stylesheet" href="${pagecontext.request.contextpath}/resources/vendor/fonts/boxicons.css" />

    <!-- Core CSS -->
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/resources/vendor/css/core.css" class="template-customizer-core-css" />
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/resources/vendor/css/theme-default.css" class="template-customizer-theme-css" />
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/resources/css/demo.css" />

    <!-- Vendors CSS -->
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/resources/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />

    <!-- Page CSS -->
    <!-- Page -->
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/resources/vendor/css/pages/page-auth.css" />

    <!-- Helpers -->
    <script src="${pagecontext.request.contextpath}/resources/vendor/js/helpers.js"></script>
    <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
    <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
    <script src="${pagecontext.request.contextpath}/resources/js/config.js"></script>
  </head>

  <body>
    <!-- Content -->

    <div class="container-xxl">
      <div class="authentication-wrapper authentication-basic container-p-y">
        <div class="authentication-inner">
          <!-- Register Card -->
          <div class="card px-sm-6 px-0">
            <div class="card-body">
              <!-- Logo -->
              <!-- /Logo -->
              <h4 class="mb-1">Adventure starts here 🚀</h4>
              <p class="mb-6">Make traveling easy and fun!</p>
              <form:form modelAttribute="user" id="formAuthentication" class="mb-6" action="/Presentsir/register" method="post">
                <div class="mb-6">
                    <label for="name" class="form-label">Name</label>
                    <form:input type="text" path="name" class="form-control" id="name" placeholder="Enter your full name" autofocus="true"/>
                </div>
                <div class="mb-6">
                    <label for="contact" class="form-label">Mobile</label>
                    <form:input type="tel" path="contact" class="form-control" id="contact" placeholder="Enter your mobile no"/>
                </div>
                <div class="mb-6">
                    <label for="email" class="form-label">Email</label>
                    <form:input type="text" path="email" class="form-control" id="email" placeholder="Enter your email"/>
                </div>
                <!-- Bus selection -->
                <div class="mb-4">
                    <label for="bus" class="form-label">Select Bus</label>
                    <select id="bus" name="busId" class="form-select">
                        <c:forEach items="${buses}" var="bus">
                            <option value="${bus.busId}">${bus.routeName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-4">
                    <label for="defaultSelect" class="form-label">Gender</label>
                    <form:select path="gender" id="defaultSelect" class="form-select">
                        <form:option value="Male" label="Male"/>
                        <form:option value="Female" label="Female"/>
                    </form:select>
                </div>
                <div class="mb-6 form-password-toggle">
                    <label class="form-label" for="password">Password</label>
                    <div class="input-group input-group-merge">
                        <form:password path="password" id="password" class="form-control" placeholder="••••••••" aria-describedby="password"/>
                        <span class="input-group-text cursor-pointer"><i class="bx bx-hide"></i></span>
                    </div>
                </div>
            
                <button type="submit" class="btn btn-primary d-grid w-100">Sign up</button>
            </form:form>
            
            </div>
          </div>
          <!-- Register Card -->
        </div>
      </div>
    </div>

    <!-- / Content -->
<!-- 
    <div class="buy-now">
      <a
        href="https://themeselection.com/item/sneat-dashboard-pro-bootstrap/"
        target="_blank"
        class="btn btn-danger btn-buy-now"
        >Upgrade to Pro</a
      >
    </div> -->

    <!-- Core JS -->
    <!-- build:js assets/vendor/js/core.js -->

    <script src="${pagecontext.request.contextpath}/resources/vendor/libs/jquery/jquery.js"></script>
    <script src="${pagecontext.request.contextpath}/resources/vendor/libs/popper/popper.js"></script>
    <script src="${pagecontext.request.contextpath}/resources/vendor/js/bootstrap.js"></script>
    <script src="${pagecontext.request.contextpath}/resources/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>
    <script src="${pagecontext.request.contextpath}/resources/vendor/js/menu.js"></script>

    <!-- endbuild -->

    <!-- Vendors JS -->

    <!-- Main JS -->
    <script src="${pagecontext.request.contextpath}/resources/js/main.js"></script>

    <!-- Page JS -->

    <!-- Place this tag before closing body tag for github widget button. -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>
  </body>
</html>
