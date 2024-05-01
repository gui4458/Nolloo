document.addEventListener('DOMContentLoaded', function() {
    let searchText = document.querySelector('.searchText').value.toLowerCase();
    let items = document.querySelectorAll('.table tbody td');

    items.forEach(function(item) {
        let text = item.textContent.toLowerCase();
        let index = text.indexOf(searchText);
        if (index >= 0) {
            let highlightedText = item.textContent.substr(0, index) +
                '<span style="background-color: rgba(0, 110, 255, 0.512);">' +
                item.textContent.substr(index, searchText.length) +
                '</span>' +
                item.textContent.substr(index + searchText.length);
            item.innerHTML = highlightedText;
        }
    });
});