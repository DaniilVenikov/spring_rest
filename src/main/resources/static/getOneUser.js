async function getOneUser(id) {
    let url = "adminApi/users/" + id;
    let response = await fetch(url);
    return await response.json();
}