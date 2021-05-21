import AxiosClient from "./clients/AxiosClient";

export const NurseService = {
    get,
    getAll,

};


async function get(id) {
    return await AxiosClient.get(`http://localhost:8080/nurses/${id}`);
}

async function getAll() {
    return await AxiosClient.get(`http://localhost:8080/nurses`);
}
