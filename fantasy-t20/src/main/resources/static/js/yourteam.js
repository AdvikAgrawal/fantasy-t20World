const showFixtures = async () => {
    const container = document.getElementById('fixturesContainer');
    let endpoint = '/api/fixtures';

    const cached = localStorage.getItem('fixtures');
    const cachedTime = localStorage.getItem('fixtures-time');

    const now = Date.now();
    const oneDay = 24 * 60 * 60 * 1000;

    if (cached && cachedTime && (now - cachedTime < oneDay)) {
        console.log("Using cached fixtures");
        renderFixtures(JSON.parse(cached), container);
        return;
    }
    
    try {
        const request = await fetch(endpoint);
        if (!request.ok) {
            throw new Error (`Server error: ${request.status}`);
        }
        const response = await request.json();
        // console.log(response);
        localStorage.setItem('fixtures', JSON.stringify(response));
        localStorage.setItem('fixtures-time', now);

        renderFixtures(response, container);
    }
    catch (error) {
        console.error('error found:', error);
    }
}

function renderFixtures(response, container) {
    container.innerHTML = "";
    const schedules = response.response.schedules;
    schedules.forEach(day => {
        const matchDate = day.scheduleAdWrapper.date;
        day.scheduleAdWrapper.matchScheduleList.forEach(listGroup => {
            listGroup.matchInfo.forEach(match => {
                const team1 = match.team1.teamName;
                const team2 = match.team2.teamName;
                const venue = match.venueInfo.ground + ", " + match.venueInfo.city;
                const card = `
                    <div class="match-card">
                        <div class="match-header">${matchDate}</div>
                        <div class="teams">
                            <strong>${team1}</strong> 
                            <span class="vs">vs</span> 
                            <strong>${team2}</strong>
                        </div>
                        <div class="match-footer">
                            <span> ${venue}</span>
                        </div>
                    </div>
                    `;
                    container.insertAdjacentHTML('beforeend', card);
                })
        })  })

}


document.addEventListener('DOMContentLoaded', showFixtures);

