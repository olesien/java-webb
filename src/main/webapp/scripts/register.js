const studentsEl = document.querySelector(".students");
const studentsButtonEl = document.querySelector(".studentsButton");
const teachersEl = document.querySelector(".teachers");
const teachersButtonEl = document.querySelector(".teachersButton");

teachersButtonEl.addEventListener("click", () => {
    console.log("Changing to add teacher view");
    teachersEl.classList.remove("hidden");
    studentsEl.classList.add("hidden");

    studentsButtonEl.classList.remove("selected");
    teachersButtonEl.classList.add("selected");
})

studentsButtonEl.addEventListener("click", () => {
    console.log("Changing to add students view");
    studentsEl.classList.remove("hidden");
    teachersEl.classList.add("hidden");

    teachersButtonEl.classList.remove("selected");
    studentsButtonEl.classList.add("selected");
})