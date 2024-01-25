// 要操作到的元素
let login=document.getElementById('login');
let getback=document.getElementById('getback');
let form_box=document.getElementsByClassName('form-box')[0];
let getback_box=document.getElementsByClassName('getback-box')[0];
let login_box=document.getElementsByClassName('login-box')[0];
// 去注册按钮点击事件
getback.addEventListener('click',()=>{
    form_box.style.transform='translateX(95%)';
    login_box.classList.add('hidden');
    getback_box.classList.remove('hidden');
})
// 去登录按钮点击事件
login.addEventListener('click',()=>{
    form_box.style.transform='translateX(0%)';
    getback_box.classList.add('hidden');
    login_box.classList.remove('hidden');
})