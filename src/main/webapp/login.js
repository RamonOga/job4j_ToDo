function validate() {
    const email = $('#email').val();
    const password = $('#password').val();

    if (email === '') {
    alert("Email field must not be empty");
    } else if (password === '') {
    alert("Password field must not be empty");
    }
     return false;
    }