import request from '@/utils/request';

export function send_create(data) {
    return request({
        url: 'send/create',
        method: 'post',
        data: data
    })
}

export function update(data) {
    return request({
        url: 'send/update',
        method: 'post',
        data: data
    })
}

export function del(ids) {
    return request({
        url: 'send/delete',
        method: 'post',
        params: {"ids": ids}
    })
}

export function detail(id) {
    return request({
        url: 'send/detail',
        method: 'post',
        params: {"id": id}
    })
}

export function query(data) {
    return request({
        url: 'send/query',
        method: 'post',
        data: data
    })
}

export function student_send(data) {
    return request({
        url: 'send/student_send',
        method: 'post',
        data: data
    });
}

export function detail_resume(data) {
    return request({
        url: 'send/detail_resume',
        method: 'post',
        data: data
    });
}