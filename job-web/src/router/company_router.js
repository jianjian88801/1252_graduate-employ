export const companyRouterMap = [
    {
        path: '/index',
        // redirect:'/company_info',
        component: () => import('@/views/Index'),
        children: [
            {
                path: '/company_info',
                name: 'company_info',
                component: () => import('@/views/company/CompanyInfo'),
            },
            {
                path: '/post',
                name: 'post',
                component: () => import('@/views/post/Post'),
            },
            {
                path: '/send',
                name: 'send',
                component: () => import('@/views/send/Send'),
            }
        ]
    }
]
