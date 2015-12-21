1. /user/login?name=testuser&password=password
    result = {
        status: 1, //1,0,-1
        uid: 1,
        name: "testuser",
        ..
        action: "borrow",
        actionIid: 1
    }

2. /view/item?iid=1
    result = {
        status: 1, //
        iid: 1,
        name: "testitem",
        owner: "",
        description: "",
        availability: 1,
        borrowUserId: 1,
        borrowUserName: "testuser",
        borrowTime: "2015-12-21 22:21:23"
        url: ""
    }

3. /view/allitem
    result = {
        available: [
            {
                iid: 1
                name: "testitem1"
            }, {
                iid: 2
                name: "testitem2"
            }, ..
        ],
        notAvailable: [
            {
                iid: 3
                name: "testitem3"
            }, {
                iid: 4
                name: "testitem4"
            }, ..
        ]
    }

4. /user/borrow?iid=1
    result = {
        status: 1, //
        uid: 1,
        name: "testuser",
        borrowItem: [
            {
                iid: 1
                name: ""testitem1
                borrowTime: "2015-12-21 22:21:23"
            }, {
                iid: 2
                name: "testitem2"
                borrowTime: "2015-12-21 22:21:23"
            }, ..
        ]
    }

5. /user/return?iid=1
    result = {
        status: 1, //
        uid: 1,
        name: "testuser",
        borrowItem: [
            {
                iid: 1
                name: testitem1"
                borrowTime: "2015-12-21 22:21:23"
            }, {
                iid: 2
                name: "testitem2"
                borrowTime: "2015-12-21 22:21:23"
            }, ..
        ]
    }