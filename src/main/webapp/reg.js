function validate() {
    const name = $('#login').val();
    const password = $('#password').val();
    if (login === '') {
        alert("Name field must not be empty")
    } else if (password === '') {
        alert("Password field must not be empty")
    }
    return false;
}