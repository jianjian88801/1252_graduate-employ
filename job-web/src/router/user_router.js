export const userRouterMap = [
    {
        path: '/index',
        // redirect:'/user',
        component: () => import('@/views/Index'),
        children: [
            {
                path: '/user',
                name: 'user',
                component: () => import('@/views/user/User'),
            },
            {
                path: '/dict',
                name: 'dict',
                component: () => import('@/views/dict/Dict'),
            },
            {
                path: '/profession',
                name: 'profession',
                component: () => import('@/views/profession/Profession'),
            },
            {
                path: '/company',
                name: 'company',
                component: () => import('@/views/company/Company'),
            },
            {
                path: '/student',
                name: 'student',
                component: () => import('@/views/student/Student'),
            },
            {
                path: '/channel',
                name: 'channel',
                component: () => import('@/views/channel/Channel'),
            },
            {
                path: '/article',
                name: 'article',
                component: () => import('@/views/article/Article'),
            },
        ]
    }
]
