//dropdown options
const loadTeams = async () => {
    let endpoint = '/api/teams';
    const request = await fetch(endpoint, {method: 'GET'});
    const response = await request.json();

    const dropdownOptions = document.getElementById("squads");
    dropdownOptions.addEventListener("change",() => {
        const teamId = dropdownOptions.value;
        showPlayers(teamId);
    })
    response.forEach(squads => {
        const option = document.createElement('option');
        option.value = squads.id;
        option.textContent = squads.name;
        dropdownOptions.appendChild(option);
        // console.log(option);
    })
}
loadTeams();
//show squad
const showPlayers = async (teamId) => {
    let endpoint = `/api/squads?squadId=${teamId}`;
    if (!teamId) {
        console.log("No team selected yet. Skipping fetch.");
        return; 
    }
    const request = await fetch(endpoint, {method: 'GET'});
    const response = await request.json();
    // console.log(response);
    const squadTable = document.getElementById('squadTable');
    squadTable.style.display = 'block';
    const tableTeam = document.getElementById('tableTeam');
    tableTeam.innerHTML = "";
    response.forEach(players => {
        const row = document.createElement('tr');
        const id = document.createElement('td');
        id.textContent = players.playerName;
        const role = document.createElement('td');
        role.textContent = players.playerRole;
        row.appendChild(id);
        row.appendChild(role);
        tableTeam.appendChild(row);
        // console.log(row);
    })
}
showPlayers();

const modal = document.getElementById("modal");
const closeBtn = document.getElementById("closeModal");
let activeSlot = null;
const selectedPlayers = new Set();
const squad = {};
document.querySelectorAll(".player-slot").forEach(slot => {
    slot.addEventListener("click", () => {
        activeSlot = slot;          
        modal.classList.add("open"); 
    });
});
closeBtn.addEventListener("click", () => {
    const input = document.getElementById("player-search");
    const playerName = input.value;
    const slotId = activeSlot.dataset.slot;
    const selectedOption = Array.from(document.getElementById("players").options)
                                .find(option => option.value === playerName);
    const playerId = parseInt(selectedOption.dataset.id);
    if ((selectedPlayers.has(playerName))) {
        if (activeSlot.querySelector(".player-id").textContent.trim() === playerName) {
            modal.classList.remove("open");
            return;
        }
        alert("Player has already been selected in another slot!");
        return;
    }
    const oldName = activeSlot.querySelector(".player-id").textContent.trim();
    if (oldName && oldName !== "") {
        selectedPlayers.delete(oldName);
    }
    squad[slotId] = playerId;
    activeSlot.querySelector(".player-id").textContent = playerName;
    selectedPlayers.add(playerName);
    console.log(squad);
    modal.classList.remove("open");
    input.value = "";
});
modal.addEventListener("click", (event) => {
    if (event.target == modal) {
        modal.classList.remove("open");
    }
})

const playerSelection = async () => {
    let endpoint = `/api/players`;
    const request = await fetch(endpoint, {method: 'GET'});
    const response = await request.json();
    const dropdownOptions = document.getElementById("players");
    dropdownOptions.innerHTML = "";
    response.forEach(players => {
        const option = document.createElement('option');
        option.value = players.playerName;
        option.dataset.id = players.id;
        dropdownOptions.appendChild(option);
    })
}
playerSelection();
async function saveSquad() {
    console.log(`function is called!`);
    const saveBtn = document.getElementById('save-squad-btn');

    if (Object.keys(squad).length != 11) {
        alert("Your squad is not complete! Add some players first.");
        return;
    }

    const playersArray = Object.keys(squad).map(slotId => {
        return {
            slot: parseInt(slotId),
            player: { id: squad[slotId] } 
        };
    });
        const response = await fetch('/api/my-team/update', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(playersArray)
        });

        if (response.ok) {
            alert("Squad saved successfully!");
        } else {
            const errorMsg = await response.text();
            alert("Failed to save: " + errorMsg);
        }
}