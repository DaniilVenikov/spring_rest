'use strict';

getCurrentUser()

function getCurrentUser() {
    fetch("/userApi")
        .then(res => res.json())
        .then(js => {
            $('#nameCurrentUser').append(`<span>${js.name}</span>`)
            $('#roleCurrentUser').append(`<span>${js.roles}</span>`)
            const user = `$(
                    <tr>
                        <td>${js.id}</td>
                        <td>${js.name}</td>
                        <td>${js.age}</td>
                        <td>${js.email}</td>
                        <td>${js.roles}</td>
                    </tr>)`;
            $('#oneUser').append(user)
        })
}