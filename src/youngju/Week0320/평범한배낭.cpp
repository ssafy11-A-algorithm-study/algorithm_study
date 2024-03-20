#include <iostream>
#include <vector>
using namespace std;

struct Tool {
    int weight;
    int value;
    void initTool(int w, int v) {
		weight = w;
		value = v;
	}
};
vector <Tool> tools;
int knapsackDP(vector<Tool>& tools, int capacity) {
    int n = tools.size();
    //dp[i][w]는 i번째 항목까지 고려한 배낭의 용량이 w일 때 얻을 수 있는 최대 가치
    vector<vector<int>> dp(n + 1, vector<int>(capacity + 1, 0));

    for (int i = 1; i <= n; ++i) {
        for (int w = 0; w <= capacity; ++w) {
            if (tools[i - 1].weight <= w) {
                dp[i][w] = max(dp[i - 1][w], dp[i - 1][w - tools[i - 1].weight] + tools[i - 1].value);
            } else {
                dp[i][w] = dp[i - 1][w];
            }
        }
    }

    return dp[n][capacity];
}

int main() {
    int n,k;
    cin >> n >> k;
	tools.resize(n+1);
	for (int i = 0; i < n; i++)
	{
		int w, v;
		cin >> w >> v;
		Tool t;
		t.initTool(w, v);
		tools[i] = t;
	}
    cout <<knapsackDP(tools, k);
    return 0;
}
