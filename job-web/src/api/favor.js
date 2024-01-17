import request from '@/utils/request';

export function favor_create(data) {
    return request({
        url: 'favor/create',
        method: 'post',
        data: data
    })
}

export function update(data) {
    return request({
        url: 'favor/update',
        method: 'post',
        data: data
    })
}

export function del(ids) {
    return request({
        url: 'favor/delete',
        method: 'post',
        params: {"ids": ids}
    })
}

export function detail(id) {
    return request({
        url: 'favor/detail',
        method: 'post',
        params: {"id": id}
    })
}

export function query(data) {
    return request({
        url: 'favor/query',
        method: 'post',
        data: data
    })
}