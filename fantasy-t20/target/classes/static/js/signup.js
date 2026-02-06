document.addEventListener('DOMContentLoaded', function() {
    const signupForm = document.querySelector('section');
    signupForm.style.opacity = 0;

    setTimeout(() => {
        signupForm.style.transition = 'opacity 1s ease-in-out';
        signupForm.style.opacity = 1;
    }, 50);


    const signupButton = document.querySelector('button');
    signupButton.addEventListener('click', function() {
        const emailInput = document.querySelector('input[type="email"]');
        const passwordInput = document.querySelector('input[type="password"]');

        const isValid = emailInput.checkValidity() && passwordInput.checkValidity();

        if (!isValid) {
            signupForm.classList.add('shake');

            setTimeout(() => {
                signupForm.classList.remove('shake');
            }, 1000);
        }
    });

})