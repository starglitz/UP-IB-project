import AxiosClient from "./clients/AxiosClient";

export const NurseService = {
    get,
    getAll,
    add,
    getByAdminsClinic
};


async function get(id) {
    return await AxiosClient.get(`https://localhost:8080/nurses/${id}`);
}

async function getAll() {
    return await AxiosClient.get(`https://localhost:8080/nurses`);
}

async function add(nurse) {
    return await AxiosClient.post("https://localhost:8080/nurses", nurse);
}

async function getByAdminsClinic() {
    return await AxiosClient.get(`https://localhost:8080/nurses/admin/clinic`);
}

