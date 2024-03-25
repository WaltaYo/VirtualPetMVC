using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace GameLibrary.Repositories
{
    /// <summary>
    /// Extension class to Read HttpResponseMessage
    /// </summary>
    public static class ApiHelper
    {
        public static async Task<TData> ReadContentAsync<TData>(this HttpResponseMessage response,CancellationToken cancellationToken = default(CancellationToken))
        {
            if (response.IsSuccessStatusCode == false)
                throw new ApplicationException($"Something went wrong calling the API: " + response.ReasonPhrase);

            String? dataAsString = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

            TData? result = JsonSerializer.Deserialize<TData>(
                dataAsString, new JsonSerializerOptions
                {
                    PropertyNameCaseInsensitive = true
                });
            if(result == null )
            {
                throw new ApplicationException($"Something went wrong calling the API: results was null");
            }
            return result;
        }
    }
}

