function updateReply(){
    const replyContent=  document.querySelector('.add-input');

    //수정 전 내용
    const now_contnet = replyContent.textContent;

    const str=`<input type="text" value="${now_contnet}">`
    replyContent.textContent='';

    replyContent.insertAdjacentHTML("afterbegin",str);
    //replyContent.setAttribute('')

}