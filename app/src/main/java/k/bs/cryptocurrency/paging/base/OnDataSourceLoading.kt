package k.bs.cryptocurrency.paging.base

interface OnDataSourceLoading {
    fun onFirstFetch()
    fun onFirstFetchEndWithData()
    fun onFirstFetchEndWithoutData()
    fun onPageLoading()
    fun onPageLoadingEnd()
    fun onError()
}