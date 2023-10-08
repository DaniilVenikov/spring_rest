async function openAndFillInTheModal(form, modal, id) {
    modal.show();
    let user = await getOneUser(id);
    form.id.value = user.id;
    form.name.value = user.name;
    form.age.value = user.age;
    form.email.value = user.email;
    form.roles.value = user.roles;
}