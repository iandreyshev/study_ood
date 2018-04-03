class TeaBeverage : AbstractBeverage() {
    fun asJavaTea() = onBrew {
        println("Add Java tea bag")
    }

    fun asGitPrincessTea() = onBrew {
        println("--Add Git tea bag")
    }
}
